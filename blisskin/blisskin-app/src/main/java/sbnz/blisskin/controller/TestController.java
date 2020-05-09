package sbnz.blisskin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sbnz.blisskin.model.TestItem;
import sbnz.blisskin.service.TestService;

@RestController(value="/test")
public class TestController {
    private static Logger log = LoggerFactory.getLogger(TestController.class);
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public ResponseEntity test(@RequestParam String name) {
        TestItem testItem = new TestItem(name);
        TestItem updatedItem = testService.updateTestItemName(testItem);
        return new ResponseEntity(updatedItem, HttpStatus.OK);
    }
}
