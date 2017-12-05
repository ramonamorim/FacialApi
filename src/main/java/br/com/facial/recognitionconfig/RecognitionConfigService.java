package br.com.facial.recognitionconfig;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import br.com.facial.persistence.AbstractService;
import br.com.facial.person.Person;
import br.com.facial.person.PersonRepo;
import br.com.facial.photo.Photo;
import br.com.facial.process.ProcessService;

@Component
@Path("recognitionconfig")
public class RecognitionConfigService extends AbstractService<RecognitionConfig, RecognitionConfigRepo> {

	@Inject
	private RecognitionConfigRepo recognitionConfigRepo;

	@Inject
	private PersonRepo personRepo;

	@Inject
	private ProcessService processService;

	@Override
	protected RecognitionConfigRepo getRepo() {
		return recognitionConfigRepo;
	}

	@GET
	@Transactional
	@Path("preparation")
	public Response prepareAndRunTrain() throws IOException, InterruptedException {
		this.limpaPastas();
		this.prepareDataOnFolder();
		this.processService.processTrain();

		return Response.ok().build();

	}

	private void limpaPastas() {
		String directoryPath = "/Users/ramonamorim/TCC/data";
		File file = new File("/Users/ramonamorim/TCC/data");
		try {
			//Deleting the directory recursively.
			delete(file);
			System.out.println("Directory has been deleted recursively !");
		} catch (IOException e) {
			System.out.println("Problem occurs when deleting the directory : " + directoryPath);
			e.printStackTrace();
		}
	}
	
	private static void delete(File file) throws IOException {
		 
		for (File childFile : file.listFiles()) {
 
			if (childFile.isDirectory()) {
				delete(childFile);
			} else {
				if (!childFile.delete()) {
					throw new IOException();
				}
			}
		}
 
		if (!file.delete()) {
			throw new IOException();
		}
	}

	@GET
	@Path("statuscomplete")
	public Response updateStatusComplete() {
		List<RecognitionConfig> listConfig = this.recognitionConfigRepo.findAll();

		if (!listConfig.isEmpty()) {
			RecognitionConfig recognitionConfig = listConfig.get(0);
			System.out.println(recognitionConfig.getCodeStatus());
			recognitionConfig.setCodeStatus("1");

			this.recognitionConfigRepo.saveAndFlush(recognitionConfig);
			System.out.println(recognitionConfig.getCodeStatus());
		}

		return Response.ok().build();
	}

	@GET
	@Path("statusprocessing")
	public Response updateStatusProcessing() {
		List<RecognitionConfig> listConfig = this.recognitionConfigRepo.findAll();

		if (!listConfig.isEmpty()) {
			RecognitionConfig recognitionConfig = listConfig.get(0);
			System.out.println(recognitionConfig.getCodeStatus());
			recognitionConfig.setCodeStatus("2");

			this.recognitionConfigRepo.saveAndFlush(recognitionConfig);
			System.out.println(recognitionConfig.getCodeStatus());
		}

		return Response.ok().build();
	}

	private void prepareDataOnFolder() throws IOException {
		new File("/Users/ramonamorim/TCC/data").mkdirs();

		List<Person> persons = this.personRepo.findAll();
		// passar como parametro no sh para definir numero de classes da rede
		int numClasses = persons.size();
		for (Person person : persons) {

			new File("/Users/ramonamorim/TCC/data/train/" + person.getName()).mkdirs();
			new File("/Users/ramonamorim/TCC/data/validation/" + person.getName()).mkdirs();

			List<Photo> photos = person.getPhotos();

			int totalPhotos = photos.size();
			int qtdValidation = (int) (totalPhotos * 0.2);
			int numberPhotosName = 0;
			for (Photo photo : photos) {

				if (qtdValidation >= 0) {
					java.nio.file.Files.write(
							(new java.io.File("/Users/ramonamorim/TCC/data/validation/" + person.getName() +"/" + numberPhotosName + ".jpg"))
									.toPath(),
							photo.getImage(), java.nio.file.StandardOpenOption.CREATE);

					qtdValidation--;

				} else {
					java.nio.file.Files
							.write((new java.io.File("/Users/ramonamorim/TCC/data/train/" +person.getName()+"/" + numberPhotosName + ".jpg"))
									.toPath(), photo.getImage(), java.nio.file.StandardOpenOption.CREATE);
				}
				numberPhotosName++;
			}

		}

	}

}
