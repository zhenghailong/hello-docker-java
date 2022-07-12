package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;

@Controller
public class ContainerController {
    @Autowired
    DockerClient dockerClient;

    @RequestMapping(value = "/hello_docker", method = RequestMethod.GET)
    public String runDocker(Model model) {
        CreateContainerResponse container1 = dockerClient.createContainerCmd("alpine")
        .withCmd("echo", "hello world")
        .withName("alpain-hellow-world")
        .exec();

        model.addAttribute("containerId", container1.getId());

        dockerClient.startContainerCmd(container1.getId()).exec();
        dockerClient.removeContainerCmd(container1.getId()).exec();

        return "hello_docker";
    }
}

