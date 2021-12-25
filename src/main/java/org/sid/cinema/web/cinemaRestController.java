package org.sid.cinema.web;

import org.springframework.http.MediaType;
import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.dao.TicketRepository;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@RestController
public class cinemaRestController {
	/*@Autowired
	private FilmRepository filmRepository;
	@GetMapping("/listFilms")
	public List<Film> films (){
		return filmRepository.findAll();
	}*/
	// par defauts JSON
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private FilmRepository filmRepository;
	// il suffit de changer le media type pour afficher des pdf/word...
	@GetMapping(path = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable (name="id") Long id) throws Exception {
		Film f =filmRepository.findById(id).get();
		String photoName = f.getPhoto();
		// utilisateur actuel
		File file = new File(System.getProperty("user.home")+"/cinema/images/"+ photoName);
		Path path =Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}
	
	// payer un ensemble des tickets 
	// faire plusieurs opérations
	@Transactional
	@PostMapping("/payerTickets")
	public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm) {
		System.out.println(ticketForm);
		List<Ticket> listTickets = new ArrayList<>();
		ticketForm.getTickets().forEach(idTicket->{
			Ticket ticket= ticketRepository.findById(idTicket).get();
			ticket.setNomClient(ticketForm.getNomClient());
			ticket.setCodePayement(ticketForm.getCodePayement());
			ticket.setReserve(true);
			ticketRepository.save(ticket);
			listTickets.add(ticket);
		});
		return listTickets;
		
	}
	
}

@Data
class TicketForm{
	private String nomClient;
	private List<Long> tickets = new ArrayList<>();
	private int codePayement;
}
