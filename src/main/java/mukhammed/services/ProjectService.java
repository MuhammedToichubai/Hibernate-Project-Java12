package mukhammed.services;

import mukhammed.dao.ProjectDao;
import mukhammed.entities.Project;

/**
 * @author Mukhammed Asantegin
 */
public class ProjectService {
    private final ProjectDao projectDao = new ProjectDao();

    public String save(Project newProject) {
        try {
            projectDao.save(newProject);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Successfully saved Project with name: "+newProject.getTitle();

    }
}
