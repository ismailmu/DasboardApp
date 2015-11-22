package com.btpns.Dashboard.server.wisma;

import java.util.ArrayList;
import java.util.List;

import com.btpns.Dashboard.client.model.wisma.Wisma;
import com.btpns.Dashboard.client.model.wisma.WismaEmployee;
import com.btpns.Dashboard.client.service.wisma.WismaService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

public class WismaServiceImpl extends RemoteServiceServlet implements WismaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public PagingLoadResult<Wisma> getWisma(FilterPagingLoadConfig config) {
		List<Wisma> wismas = new ArrayList<Wisma>();
		WismaPersistence persistence = new WismaPersistence();
		
		List<WismaModel> wismaModels = persistence.getWismaModel(
				config.getOffset(), config.getLimit(), config.getSortInfo(), config.getFilters());
		
		Integer no=0;
		for(WismaModel model:wismaModels) {
			no++;
			wismas.add(new Wisma(config.getOffset()+no,model.getId(),model.getWisma(),model.getWismaName()
					,model.getKcs(),model.getKcsName(),model.getAddress(),model.getRtrw(),model.getKelurahan()
					,model.getKecamatan(),model.getKabupaten(),model.getPropinsi(),model.getZipcode(),model.getTelephone()));
		}
		return new PagingLoadResultBean<Wisma>(wismas,persistence.getSize(), config.getOffset());
	}

	@Override
	public PagingLoadResult<WismaEmployee> getWismaEmployee(
			FilterPagingLoadConfig config, Wisma wisma) {
		
		List<WismaEmployee> wismas = new ArrayList<WismaEmployee>();
		WismaPersistence persistence = new WismaPersistence();
		
		List<WismaEmployeeModel> wismaModels = persistence.getWismaEmployeeModel(
				config.getOffset(), config.getLimit(), config.getSortInfo(), config.getFilters(),wisma);
		
		Integer no=0;
		for(WismaEmployeeModel model:wismaModels) {
			no++;
			wismas.add(new WismaEmployee(config.getOffset()+no,model.getId(),model.getWisma(),model.getEmployeeName()
					,model.getEmail(),model.getTelephone(),model.getHp(),model.getPosition()));
		}
		return new PagingLoadResultBean<WismaEmployee>(wismas,persistence.getSize(), config.getOffset());
	}
}