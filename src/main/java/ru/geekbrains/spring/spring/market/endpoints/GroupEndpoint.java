package ru.geekbrains.spring.spring.market.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.spring.spring.market.services.GroupService;
import ru.geekbrains.spring.spring.market.soap.groups.*;

@Endpoint
@RequiredArgsConstructor
public class GroupEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.ru/spring/spring/market/groups";
    private final GroupService groupService;

    /*
        Пример запроса: POST http://localhost:8080/market

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.geekbrains.ru/spring/spring/market/groups">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getGroupByTitleRequest>
                    <f:title>ABC-123</f:title>
                </f:getGroupByTitleRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGroupByTitleRequest")
    @ResponsePayload
    @Transactional
    public GetGroupByTitleResponse getGroupByTitle(@RequestPayload GetGroupByTitleRequest request) {
        GetGroupByTitleResponse response = new GetGroupByTitleResponse();
        response.setGroup(groupService.getByTitle(request.getTitle()));
        return response;
    }
}
