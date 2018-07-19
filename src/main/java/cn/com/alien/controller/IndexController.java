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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Root on 2017/6/25.
 */
@RestController
public class IndexController {

    @Autowired
    DiscoveryClient client;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void index(HttpServletResponse response) {
        try {
            List<ServiceInstance> list = client.getInstances("CloudServer");
            List<String> listServices = client.getServices();
            for (String s:
                    listServices) {
                response.getWriter().println(s);
            }
            for (ServiceInstance s:
                 list) {
                response.getWriter().println(s.getUri());
                response.getWriter().print((new RestTemplate()).getForObject(s.getUri()+"/info",String.class));
                RestTemplate restTemplate = new RestTemplate();
                Map<String, String> map = new HashMap<String, String>();
                map.put("name","abc");
//                RequestEntity<Map> mapRequestEntity = new RequestEntity<Map>(map, HttpMethod.POST, s.getUri());
//
//                ResponseEntity<String> resp = restTemplate.exchange(s.getUri()+"/userLogin",HttpMethod.POST, mapRequestEntity, String.class);
//                response.getWriter().println(resp.getBody());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
