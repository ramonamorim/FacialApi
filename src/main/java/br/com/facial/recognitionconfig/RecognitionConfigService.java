package br.com.facial.recognitionconfig;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import br.com.facial.persistence.AbstractService;
import br.com.facial.person.Person;
import br.com.facial.person.PersonRepo;
import br.com.facial.photo.Photo;

@Component
@Path("recognitionconfig")
public class RecognitionConfigService extends AbstractService<RecognitionConfig, RecognitionConfigRepo> {

	@Inject
	private RecognitionConfigRepo recognitionConfigRepo;

	@Inject
	private PersonRepo personRepo;

	@Override
	protected RecognitionConfigRepo getRepo() {
		return recognitionConfigRepo;
	}

	@GET
	@Transactional
	@Path("preparation")
	private Response prepareAndRunTrain() throws IOException {
		this.prepareDataOnFolder();
//		ProcessBuilder builder = new ProcessBuilder();

		return Response.ok().build();

	}

	private void prepareDataOnFolder() throws IOException {
		new File("/Users/ramonamorim/TCC/data").mkdir();

		List<Person> persons = this.personRepo.findAll();
		// passar como parametro no sh para definir numero de classes da rede
		int numClasses = persons.size();
		for (Person person : persons) {

			new File("/Users/ramonamorim/TCC/data/train/" + person.getName()).mkdir();
			new File("/Users/ramonamorim/TCC/data/validation/" + person.getName()).mkdir();

			List<Photo> photos = person.getPhotos();

			int totalPhotos = photos.size();
			int qtdValidation = (int) (totalPhotos * 0.2);
			int numberPhotosName = 0;
			for (Photo photo : photos) {

				if (qtdValidation >= 0) {
					java.nio.file.Files.write(
							(new java.io.File("/Users/ramonamorim/TCC/data/validation/" + numberPhotosName + ".jpg"))
									.toPath(),
							photo.getImage(), java.nio.file.StandardOpenOption.CREATE);

					qtdValidation--;

				} else {
					java.nio.file.Files
							.write((new java.io.File("/Users/ramonamorim/TCC/data/train/" + numberPhotosName + ".jpg"))
									.toPath(), photo.getImage(), java.nio.file.StandardOpenOption.CREATE);
				}
				numberPhotosName++;
			}

		}

	}

}
