package com.iac.onboarding.service;

import com.iac.onboarding.model.*;
import com.iac.onboarding.repository.*;
import com.iac.onboarding.util.UTMGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UTMRepository utmRepository;

    @Autowired
    private TrackingRepository trackingRepository;

    //  Register user + signup tracking
    public User registerUser(User user, String ref) {

        // Save user
        User savedUser = userRepository.save(user);

        // Generate UTM
        String utmCode = UTMGenerator.generateCode(savedUser);
        String utmLink = "http://localhost:5173/register?ref=" + utmCode;

        UTM utm = new UTM();
        utm.setUtmCode(utmCode);
        utm.setUtmLink(utmLink);
        utm.setUser(savedUser);
        utmRepository.save(utm);

        savedUser.setUtm(utm);

        // Create tracking
        Tracking tracking = new Tracking();
        tracking.setClicks(0);
        tracking.setSignups(0);
        tracking.setUser(savedUser);
        trackingRepository.save(tracking);

        savedUser.setTracking(tracking);

        //  Signup tracking
        if (ref != null && !ref.isEmpty()) {
            UTM refUtm = utmRepository.findByUtmCode(ref);

            if (refUtm != null) {
                Tracking refTracking = refUtm.getUser().getTracking();
                refTracking.setSignups(refTracking.getSignups() + 1);
                trackingRepository.save(refTracking);
            }
        }

        return savedUser;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get UTM
    public UTM getUTMByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUtm();
    }

    // Track clicks
    public void trackClick(String utmCode) {

        UTM utm = utmRepository.findByUtmCode(utmCode);

        if (utm == null) {
            throw new RuntimeException("UTM not found");
        }

        Tracking tracking = utm.getUser().getTracking();
        tracking.setClicks(tracking.getClicks() + 1);

        trackingRepository.save(tracking);
    }
}