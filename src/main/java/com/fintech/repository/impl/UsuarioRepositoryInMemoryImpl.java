package com.fintech.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.fintech.model.conta.Conta;
import com.fintech.model.usuario.Usuario;
import com.fintech.repository.UsuarioRepository;

public class UsuarioRepositoryInMemoryImpl implements UsuarioRepository {
	
	private List<Usuario> users = new ArrayList<Usuario>();
	
	private static final UsuarioRepositoryInMemoryImpl instance = new UsuarioRepositoryInMemoryImpl();

	private UsuarioRepositoryInMemoryImpl() {

	}

	public static UsuarioRepositoryInMemoryImpl getInstance(){
		return instance;
	}

	@Override
	public Usuario inserir(Usuario user) {
		if (!users.contains(user)) {
		users.add(user);
		}
		return user;
	}

	@Override
	public void excluir(Usuario user) {	
		for (Conta conta : user.getContas()) {
				ContaRepositoryInMemoryImpl.getInstance().excluir(conta.getNumeroConta());
		}
		
		users.remove(user);
		
	}

	@Override
	public void vincularConta(Usuario user, Conta conta) {
		user.getContas().add(conta);
	}

	@Override
	public void desvincularConta(Usuario user, String numeroConta) {
		for(Conta conta : user.getContas()) {
			if (conta.getNumeroConta() == numeroConta) {
				user.getContas().remove(conta);
			}
		}
		
	}
	
	@Override
	public List<Usuario> listarUsuarios() {
		return users;
	}

}
