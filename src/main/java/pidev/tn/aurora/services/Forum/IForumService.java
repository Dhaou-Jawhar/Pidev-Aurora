package pidev.tn.aurora.services.Forum;

import pidev.tn.aurora.entities.Forum.Publication;

import java.util.List;

public interface IForumService {
    Publication addPub (Publication pub);
    List<Publication> DisplayPublication();
    Publication update(Publication pub);
    void delete(Integer id);
    Publication showPub (Integer id);


    /////////////////////////////////////////////////
    public void addUser(String username);
    public void removeUser(String username);
    public List<String> getActiveUsers();

}
