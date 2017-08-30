package br.com.facial.servico;

import br.com.facial.persistencia.BaseEntidade;
import br.com.facial.persistencia.BaseRepositorio;

public abstract class AbstractCrudService<T extends BaseEntidade, J extends BaseRepositorio<T>> {

	protected abstract J getRepository();
}
