package cn.com.alien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Root on 2017/6/25.
 */
@RestController
public class IndexController {

    @Autowired
    DiscoveryClient client;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void index(HttpServletResponse response) {
        try {
            List<ServiceInstance> list = client.getInstances("cloudServer");
            for (ServiceInstance s:
                 list) {
                response.getWriter().print(s.getUri());
                response.getWriter().print((new RestTemplate()).getForObject(s.getUri(),String.class));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
