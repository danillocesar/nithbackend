package br.com.nith.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nith.dao.VideoDao;
import br.com.nith.models.Video;

@RestController
@CrossOrigin
public class VideoController {

	@Autowired
	VideoDao videoDao;
	
	@GetMapping("/videos")
	public List<Video> getVideos(){
		return videoDao.listAll();
	}
}
