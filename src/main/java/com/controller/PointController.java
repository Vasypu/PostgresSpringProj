package com.controller;

import com.model.Point;
import com.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/points")
public class PointController {
    @Autowired
    PointRepository pointRepository;

    @RequestMapping("/findall")
    public String findAll() {
        String result = "";

        for (Point cust : pointRepository.findAll()) {
            result += cust.toString() + "<br>";
        }

        return result;
    }

    @PostMapping(value = "/newpoint")
    public ResponseEntity<?> create(@RequestBody Point point) {
        pointRepository.save(point);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Point>> read() {
        final List<Point> points = pointRepository.findAll();

        return points != null && !points.isEmpty()
                ? new ResponseEntity<>(points, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/point/{id}")
    public ResponseEntity<Optional<Point>> getPoint(@PathVariable(name = "id") long id) {
        final Optional<Point> point = pointRepository.findById(id);

        return point != null
                ? new ResponseEntity<>(point, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//	@PutMapping(value = "/clients/{id}")
//	public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Point point) {
//		final boolean updated = pointRepository.update(point, id);
//
//		return updated
//				? new ResponseEntity<>(HttpStatus.OK)
//				: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//	}

    @DeleteMapping(value = "/deletepoint/{id}")
    public void /*ResponseEntity<?>*/ delete(@PathVariable(name = "id") long id) {
        pointRepository.deleteById(id);

//		return deleted
//				? new ResponseEntity<>(HttpStatus.OK)
//				: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
