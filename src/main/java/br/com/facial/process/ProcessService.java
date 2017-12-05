package br.com.facial.process;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.io.IOUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ProcessService {

	@Async
	public CompletableFuture<Void> processTrain() throws IOException, InterruptedException {
		System.out.println("Chegou ...");
		Process p = Runtime.getRuntime().exec(
				new String[] { "/Library/Frameworks/Python.framework/Versions/3.6/bin/python3", "vggpretrainedoriginal.py  5" },
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
