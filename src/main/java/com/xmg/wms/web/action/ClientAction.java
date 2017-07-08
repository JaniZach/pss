package com.xmg.wms.web.action;

import com.xmg.wms.domain.Client;
import com.xmg.wms.query.ClientQueryObject;
import com.xmg.wms.service.IClientService;
import com.xmg.wms.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class ClientAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IClientService clientService;

	@Getter
	private ClientQueryObject qo=new ClientQueryObject();

	@Getter
	private Client client = new Client();
	
	@RequiredPermission("客户列表")
	public String execute(){
		try {
			putContext("result", clientService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("客户编辑")
	public String input() {
		try {
			if (client.getId() != null) {
                client = clientService.get(client.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("客户保存/更新")
	public String saveOrUpdate() {
		try {
			if (client.getId() == null) {
                clientService.save(client);
				addActionMessage("增加成功");
            } else {
                clientService.update(client);
				addActionMessage("修改成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("客户删除")
	public String delete()  throws  Exception {
		try {
			if (client.getId() != null) {
                clientService.delete(client.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}
	@RequiredPermission("客户批量删除")
	public String batchDelete() throws Exception {
		try {
			if (this.ids.size() != 0) {
		clientService.batchDelete(ids);
			putMsg("批量删除成功");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			addActionError(e1.getMessage());
		}
		return NONE;
	}
}
