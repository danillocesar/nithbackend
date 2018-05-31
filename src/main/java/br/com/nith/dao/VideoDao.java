package br.com.nith.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.nith.models.Video;



@Repository
@Transactional
public class VideoDao extends AbstractDao<Integer, Video> {
	
}
