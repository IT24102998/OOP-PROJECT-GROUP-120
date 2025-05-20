// UserService.java - Dummy content for scaffolding
package com.eventphotography.service;

import com.eventphotography.model.User;
import com.eventphotography.util.FileHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {
    private final FileHandler<User> fileHandler;
    private final List<User> users;

    public UserService() {
        this.fileHandler = new FileHandler<>("users.txt");
        this.users = new ArrayList<>();
        loadUsers();
    }

    private void loadUsers() {
        try {
            List<String> lines = fileHandler.readLines();
            for (String line : lines) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String email = parts[2];
                String role = parts[3];
                users.add(new User(id, name, email, role));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) throws IOException {
        users.add(user);
        fileHandler.writeList(users);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public void updateUser(User updatedUser) throws IOException {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == updatedUser.getId()) {
                users.set(i, updatedUser);
                break;
            }
        }
        fileHandler.writeList(users);
    }

    public void deleteUser(int id) throws IOException {
        users.removeIf(u -> u.getId() == id);
        fileHandler.writeList(users);
    }
}