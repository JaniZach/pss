package com.xmg.wms.web.action;

import com.alibaba.druid.util.StringUtils;
import com.xmg.wms.domain.Product;
import com.xmg.wms.query.ProductQueryObject;
import com.xmg.wms.service.IBrandService;
import com.xmg.wms.service.IProductService;
import com.xmg.wms.util.FileUploadUtil;
import com.xmg.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

public class ProductAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IProductService productService;
    @Setter
    private IBrandService brandService;

    @Getter
    private ProductQueryObject qo = new ProductQueryObject();

    @Getter
    private Product product = new Product();
    //上传文件,接收对象File及文件名picFileName
    @Setter
    private File pic;
    @Setter
    private String picFileName;
    @Setter
    private String picContentType;

    @RequiredPermission("商品管理列表")
    public String execute() {
        try {
            //将brands信息共享到值栈context区域
            putContext("brands", brandService.list());
            putContext("result", productService.pageQuery(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @RequiredPermission("商品管理编辑")
    public String input() {
        try {
            //将brands信息共享到值栈context区域
            putContext("brands", brandService.list());
            if (product.getId() != null) {
                product = productService.get(product.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return INPUT;
    }

    @RequiredPermission("商品管理保存/更新")
    public String saveOrUpdate() {
        try {
            if (this.pic != null) {
                //当有文件上传时,将其路径保存到数据库
                String imagPath = FileUploadUtil.uploadFile(pic, picFileName);
                product.setImagePath(imagPath);
            }
            if (product.getId() == null) {
                //新增时候,数据库数据库中没有图片的存放地址
                this.clearMessages();//先清空下actionMessage中的信息,解决框架往里面添加一些信息的情况
                productService.save(product);
                addActionMessage("增加成功");
            } else {
                //编辑时候,数据库数据库中可能存在图片的存放地址
                Product oldOne = productService.get(this.product.getId());
                if (this.pic!=null && !StringUtils.isEmpty(oldOne.getImagePath())) {
                    //如果修改时,要修改图片的话,先删除原来的,再保存
                    //如果修改时,不修改图片,不管图片这一列即可(同过sql实现)
                    FileUploadUtil.deleteFile(oldOne.getImagePath());
                }
                    //如果原来没有图片,直接保存(注意,没有文件,则不更新图片)
                productService.update(this.product);
                this.clearMessages();
                addActionMessage("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("商品管理删除")
    public String delete() throws Exception {
        try {
            if (product.getId() != null) {
                String oldPath = productService.get(product.getId()).getImagePath();
                if(!StringUtils.isEmpty(oldPath)){
                    //删除时,如果有图片的话,先删除服务器中保存的图片
                    FileUploadUtil.deleteFile(oldPath);
                }
                productService.delete(product.getId());
                putMsg("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putMsg(e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("商品管理批量删除")
    public String batchDelete() throws Exception {
        try {
            if (this.ids.size() != 0) {
                for (Long id : ids) {
                    String oldPath = productService.get(id).getImagePath();
                    if(!StringUtils.isEmpty(oldPath)){
                        //删除时,如果有图片的话,先删除服务器中保存的图片
                        FileUploadUtil.deleteFile(oldPath);
                    }
                }
                productService.batchDelete(ids);
                putMsg("批量删除成功");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            addActionError(e1.getMessage());
        }
        return NONE;
    }
    public String selectProduct(){
        try {
            //将brands信息共享到值栈context区域
            putContext("brands", brandService.list());
            putContext("result", productService.pageQuery(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        //跳转到商品选择页面
        return "selectProduct";
    }
}
