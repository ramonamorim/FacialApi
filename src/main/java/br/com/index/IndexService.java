package br.com.index;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import br.com.facial.persistence.AbstractService;

@Component
@Path("index")
public class IndexService extends AbstractService<Index, IndexRepo> {
	
	@Inject 
	private IndexRepo indexRepo;

	@Override
	protected IndexRepo getRepo() {
		return indexRepo;
	}

}
