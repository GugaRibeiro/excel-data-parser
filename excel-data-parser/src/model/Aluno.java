package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Aluno {
	private String nome;
	private String matricula;
	private String curso;

	//cada posição representa um dia útil começando da segunda
	List<Boolean> almoco = new ArrayList<Boolean>();  
	List<Boolean> jantar = new ArrayList<Boolean>();
	
	public Aluno() {
		/*Default constructor*/
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public List<Boolean> getAlmoco() {
		return almoco;
	}

	public void setAlmoco(List<Boolean> almoco) {
		this.almoco = almoco;
	}

	public List<Boolean> getJantar() {
		return jantar;
	}

	public void setJantar(List<Boolean> jantar) {
		this.jantar = jantar;
	}

	@Override
	public String toString() {
		return "Aluno [nome=" + nome + ", matricula=" + matricula + ", curso=" + curso + ", almoco="
				+ almoco.toString() + ", jantar=" + jantar.toString() + "]";
	}
	
	
}
