package com.project.covid19.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.covid19.entity.Board;
import com.project.covid19.entity.Person;
import com.project.covid19.repository.BoardRepository;
import com.project.covid19.repository.PersonRepository;
import com.project.covid19.service.BoardService;
import com.project.covid19.vo.PersonDto;
import com.project.covid19.vo.PersonMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Modifier;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository repo;

    @Autowired
    PersonRepository personRepository;

    @Override
    public boolean writeBoard(Board board) {
        return repo.save(board) != null ? true : false;
    }

    @Transactional
    @Override
    public List<Board> getBoardList(Pageable pageable) {

        Person person = new Person();
        person.setName("sdf");
        personRepository.save(person);

        ModelMapper mapper = new ModelMapper();
        Page<PersonDto> collect = personRepository.findAllBy(Person.class, pageable)
                .map(entity -> mapper.map(entity, PersonDto.class));

        final List<PersonDto> content = collect.getContent();
        final Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .create();
        final String s = gson.toJson(content, content.getClass());
        System.out.println("s = " + s);


        final Page<PersonMap> collect2 = personRepository.findAllBy(PersonMap.class, pageable);
//        System.out.println("collect2 = " + collect2.getContent());
        final Page<PersonDto> map = collect2.map(entity -> mapper.map(entity, PersonDto.class));
        System.out.println("map = " + collect2.getContent());

        return repo.findAll();
    }

    @Override
    public Board readByBoardNo(Long boardNo) {
        return repo.findByBoardNo(boardNo);
    }

    @Override
    public boolean removeBoard(Long boardNo) {
        repo.deleteById(boardNo);
        return repo.findByBoardNo(boardNo) == null ? true : false;
    }

    @Override
    public boolean modifyBoard(Board board) {
        Board entity = repo.findByBoardNo(board.getBoardNo());

        entity.setTitle(board.getTitle());
        entity.setContents(board.getContents());

        return repo.save(entity) != null ? true : false;
    }
}
