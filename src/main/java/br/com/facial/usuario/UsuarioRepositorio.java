package br.com.facial.usuario;

import java.util.List;

import br.com.facial.persistencia.BaseRepositorio;

public interface UsuarioRepositorio extends BaseRepositorio<Usuario> {

	List<Usuario> findUsuarioById(String id);
	
	Usuario findByNomeusuario(String nomeusuario);

}
