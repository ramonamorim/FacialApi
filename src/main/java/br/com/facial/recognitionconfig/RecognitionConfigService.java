package br.com.facial.recognitionconfig;

import javax.inject.Inject;

import br.com.facial.persistence.AbstractService;

public class RecognitionConfigService extends AbstractService<RecognitionConfig, RecognitionConfigRepo> {

	@Inject
	private RecognitionConfigRepo recognitionConfigRepo;
	
	@Override
	protected RecognitionConfigRepo getRepo() {
		return recognitionConfigRepo;
	}

}
