package mukhammed.services;

import mukhammed.dao.CompanyDao;
import mukhammed.dao.ProjectDao;
import mukhammed.entities.Company;
import mukhammed.entities.Project;

/**
 * @author Mukhammed Asantegin
 */
public class ProjectService {
    private final ProjectDao projectDao = new ProjectDao();
    private final CompanyDao companyDao = new CompanyDao();

    public String save(Project newProject) {
        try {
            projectDao.save(newProject);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Successfully saved Project with name: " + newProject.getTitle();

    }

    public String save(Long companyId, Project newProject) {
        Company company = null;
        try {
            company = companyDao.findById(companyId)
                    .orElseThrow(() ->
                            new RuntimeException("Company with id: " + companyId + " not found"));
        } catch (RuntimeException e) {
            return e.getMessage();
        }
        projectDao.save(companyId, newProject);
        return "Project successfully saved, with name: " + newProject.getTitle();

    }

    public String assignProjectToCompany(Long companyId, Long projectId) {
        try {
            Company company = companyDao.findById(companyId)
                    .orElseThrow(() ->
                            new RuntimeException("Company with id: " + companyId + " not found"));

            Project project = findById(projectId);
            company.getProjects().add(project);
            project.setCompany(company);
        } catch (RuntimeException e) {
           return e.getMessage();
        }
        projectDao.assignProjectToCompany(companyId, projectId);

        return null;
    }

    private Project findById(Long id) {
        return projectDao.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project with id: " + id + " not found!"));
    }
}
