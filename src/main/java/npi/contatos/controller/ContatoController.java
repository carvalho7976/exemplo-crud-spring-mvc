package npi.contatos.controller;

import javax.inject.Inject;

import org.jboss.logging.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import java.util.*;
import npi.contatos.model.Contato;
import npi.contatos.model.JsonTableResponse;
import npi.contatos.model.JsonTableResult;
import npi.contatos.service.ContatoService;

@Controller
public class ContatoController {
	
	@Inject
	private ContatoService contatoService;
	
	@RequestMapping(value = "/")
	public String index() {
		return "redirect:/listar";
	}
	
	@RequestMapping(value = "/listar")
	public String listar(Model model) {
		model.addAttribute("contatos", contatoService.findAll());
		return "listar";
	}
	
	@RequestMapping(value="/listarJson", method = RequestMethod.POST,headers="Accept=application/json")
	@ResponseBody public JsonTableResponse<Contato> listContatos(){
		List<Contato> contatos = contatoService.findAll();
		
		return  new JsonTableResponse<Contato>("OK", contatos, contatos.size());		
	}
	
	@RequestMapping(value = "/adicionarJson", method = RequestMethod.POST, consumes="application/json",headers = "content-type=application/x-www-form-urlencoded")
	@ResponseBody public JsonTableResult<Contato> adicionarJson(@RequestParam("nome") String nome, @RequestParam("telefone") String telefone, @RequestParam("email") String email){
		//
		Contato contato = new Contato();
		//contato.setId(id);
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setTelefone(telefone);
		System.out.println(contato.toString());
		try {
			contatoService.salvar(contato);	
			return new JsonTableResult<Contato>("OK", contato);
		}catch(Exception e){
			return new JsonTableResult<Contato>("ERROR", e.getMessage());
		}
		
	}
	@RequestMapping(value = "/editarJson", method = RequestMethod.POST, consumes="application/json",headers = "content-type=application/x-www-form-urlencoded")
	@ResponseBody public JsonTableResult<Contato> editarJson(@RequestParam("id") Integer id,@RequestParam("nome") String nome, @RequestParam("telefone") String telefone, @RequestParam("email") String email){
		
		Contato contato = contatoService.findById(id);
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setTelefone(telefone);
		System.out.println(contato.toString());
		try {
			contatoService.salvar(contato);	
			return new JsonTableResult<Contato>("OK", contato);
		}catch(Exception e){
			return new JsonTableResult<Contato>("ERROR", e.getMessage());
		}
		
	}
	@RequestMapping(value = "/deletarJson", method = RequestMethod.POST, consumes="application/json",headers = "content-type=application/x-www-form-urlencoded")
	@ResponseBody public JsonTableResult<Contato> deletartarJson(@RequestParam("id") Integer id){
		
		try {
			contatoService.remover(id);
			return new JsonTableResult<Contato>("OK", null);
		}catch(Exception e){
			return new JsonTableResult<Contato>("ERROR", e.getMessage());
		}
		
	}
	
	
	@RequestMapping(value = "/adicionar", method = RequestMethod.GET)
	public String adicionarForm(Model model) {
		model.addAttribute("contato", new Contato());
		return "adicionar";
	}
	
	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public String adicionar(@ModelAttribute("contato") Contato contato) {
		contatoService.salvar(contato);
		return "redirect:/listar";
	}
	
	@RequestMapping(value = "/remover/{id}", method = RequestMethod.GET)
	public String remover(@PathVariable("id") Integer id) {
		contatoService.remover(id);
		return "redirect:/listar";
	}
	@RequestMapping(value="/crud",method = RequestMethod.GET)
	public String crud(){
		return "crud";
	}

}
