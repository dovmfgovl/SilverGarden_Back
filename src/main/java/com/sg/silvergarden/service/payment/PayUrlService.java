package com.sg.silvergarden.service.payment;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface PayUrlService {
    String generateUrl(Map<String, Object> pmap);
    String getToken();
}
