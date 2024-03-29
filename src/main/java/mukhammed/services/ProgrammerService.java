package mukhammed.services;

import mukhammed.dao.ProgrammerDao;
import mukhammed.dao.ProjectDao;
import mukhammed.entities.Programmer;

import java.util.List;
import java.util.Map;

/**
 * @author Mukhammed Asantegin
 */
public class ProgrammerService {
    private final ProgrammerDao programmerDao = new ProgrammerDao();
    private final ProjectDao projectDao = new ProjectDao();

    public String save(Programmer newProgrammer) {
       return programmerDao.save(newProgrammer);
    }

    public String assignProgrammerToProject(Long programmerId, Long projectId){
        try {
            findById(programmerId);
            projectDao.findById(projectId)
                    .orElseThrow(() ->
                            new RuntimeException("Project with id: " + projectId + " not found!"));
        }catch (RuntimeException e){
            return e.getMessage();
        }
        return  programmerDao.assignProgrammerToProject(programmerId, projectId);
    }

    public Programmer findById(Long id) {
        return programmerDao.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Programmer with id: "+id+" not found!"));
    }

    public String assignProgrammersToProject(List<Long> programmersIds, Long projectId) {
       try {
           for (Long programmerId : programmersIds) {
               findById(programmerId);
           }
           projectDao.findById(projectId)
                   .orElseThrow(() ->
                           new RuntimeException("Project with id: " + projectId + " not found!"));
       }catch (RuntimeException e){
           return e.getMessage();
       }
        return programmerDao.assignProgrammersToProject(programmersIds, projectId);
    }

    public String deleteById(Long id) {
        try {
            findById(id);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
        return programmerDao.deleteById(id);
    }

    public String update(Long id, Programmer programmer) {
        try {
            findById(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return programmerDao.update(id, programmer);
    }

    public List<Programmer> findAll() {
        return programmerDao.findAll();
    }

    public Map<String, List<Programmer>> groupProgrammersByCompanyName() {
       return programmerDao.groupProgrammersByCompanyName();
    }
}
