package br.com.facial.index;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.facial.persistence.AbstractService;

@Component
@Path("model")
public class IndexService extends AbstractService<Index, IndexRepo> {

	@Inject
	private IndexRepo indexRepo;

	@Override
	protected IndexRepo getRepo() {
		return indexRepo;
	}

	@POST
	@Path("reg")
	@Transactional
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String getIndices(String value) {

		this.indexRepo.deleteAll();;
		// List<Index> indexList = this.indexRepo.findAll();

		// for (Index index : indexList) {
		// this.indexRepo.deleteByNome(index.getNome());
		// }

		// Map<String, Integer> values = new HashMap<>();
		// JSONObject json = new JSONObject("{'Astir': 0, 'Billy Burke': 1,
		// 'Camila': 2, 'Carla': 3, 'Gabriel': 4, 'George Clooney': 5, 'Jared':
		// 6, 'Jessica': 7, 'Juliane': 8, 'Murilo': 9, 'Ramon': 10, 'Vanessa':
		// 11}");
		JSONObject json = new JSONObject(value);
		Iterator<?> keys = json.keys();
		while (keys.hasNext()) {
			String name = (String) keys.next();

			int codigo = json.getInt(name);

			Index index = new Index();
			index.setNome(name);
			index.setCodigo(String.valueOf(codigo));

			this.indexRepo.saveAndFlush(index);

		}

		return null;
	}
}