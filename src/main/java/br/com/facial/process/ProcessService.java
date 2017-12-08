package br.com.facial.process;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.io.IOUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ProcessService {

	@SuppressWarnings("unused")
	@Async
	public CompletableFuture<Void> processTrain(String numPersons) throws IOException, InterruptedException {
		System.out.println("Chegou ...");

		int persons = Integer.valueOf(numPersons);
		persons = persons + 3;

		Process p = Runtime
				.getRuntime().exec(
						new String[] { "/Library/Frameworks/Python.framework/Versions/3.6/bin/python3",
								"vggpretrainedfinal.py", String.valueOf(persons) },
						null, new File("/Users/ramonamorim/TCC/"));
		int result = p.waitFor();
		if (p != null) {
			System.out.println("Chegou ... no if");

			System.out.println(IOUtils.toString(p.getInputStream(), "UTF-8"));

		}
		System.out.println(" ... temrinou");
		return null;
	}

}
