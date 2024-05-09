import React, { useEffect, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash, faPen, faEye } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import Todo from './Todo';
import { Link } from 'react-router-dom';

function Project() {
    const [allProjects, setAllProjects] = useState([]);
    const [newTitle, setNewTitle] = useState("");
    const [newDescription, setNewDescription] = useState("");
    const [currentEdit, setCurrentEdit] = useState(null); // Current editing index
    const [currentEditedProject, setCurrentEditedProject] = useState({ title: "", description: "" });

    // Load projects from local storage on component mount
    useEffect(() => {
        const storedProjects = JSON.parse(localStorage.getItem('projectlist'));
        if (storedProjects) {
            setAllProjects(storedProjects);
        }
    }, []);

    const handleAddProject = () => {
        if (newTitle && newDescription) {
            const newProjectItem = {
                title: newTitle,
                description: newDescription,
            };

            const updatedProjectArr = [...allProjects, newProjectItem];
            setAllProjects(updatedProjectArr);
            localStorage.setItem('projectlist', JSON.stringify(updatedProjectArr));

            // Clear input fields after adding a project
            setNewTitle("");
            setNewDescription("");
        } else {
            alert("Please provide both title and description for the project.");
        }
    };

    const handleDeleteProject = (index) => {
        const reducedProjects = [...allProjects];
        reducedProjects.splice(index, 1);
        setAllProjects(reducedProjects);
        localStorage.setItem('projectlist', JSON.stringify(reducedProjects));
    };

    const handleEditProject = (index) => {
        setCurrentEdit(index);
        setCurrentEditedProject(allProjects[index]);
    };

    const handleUpdateProject = () => {
        const updatedProjectArr = [...allProjects];
        updatedProjectArr[currentEdit] = currentEditedProject;
        setAllProjects(updatedProjectArr);
        localStorage.setItem('projectlist', JSON.stringify(updatedProjectArr));
        setCurrentEdit(null); // Reset editing index
    };

    const navigate = useNavigate(); // Initialize useNavigate

    const handleViewProject = (index) => {
        // Logic for viewing project
        const projectToView = allProjects[index];
        
        // Check if the project has todos
        if (projectToView.todos.length > 0) {
            // Navigate to the Todo page with the project data
            navigate('/Todo', { state: { project: projectToView } });
        } else {
            // Navigate to the Todo page with a message
            navigate('/Todo', { state: { message: "No todos created for this project." } });
        }
    };

    return (
        <div className='project-main'>
            <h1>My Projects</h1>
            <div className='project-wrapper'>
                <div className='project-input'>
                    <div className='project-input-item'>
                        <label>Title</label>
                        <input type='text' value={newTitle} onChange={(e) => setNewTitle(e.target.value)} placeholder='Title for project' />
                    </div>
                    <div className='project-input-item'>
                        <label>Description</label>
                        <input type='text' value={newDescription} onChange={(e) => setNewDescription(e.target.value)} placeholder='Description for project' />
                    </div>
                    <div className='project-input-item'>
                        <button type='button' onClick={handleAddProject} className='primaryButton'>Add</button>
                    </div>
                </div>

                <div className='project-list'>
                    {allProjects.map((project, index) => (
                        <div className="project-list-item" key={index}>
                            <div>
                        <h3>{project.title}</h3>
                                <p>{project.description}</p>
                            </div>
                            <div>
                                <FontAwesomeIcon className='icon' onClick={() => handleDeleteProject(index)} icon={faTrash} />
                                <FontAwesomeIcon className='edit-icon' onClick={() => handleEditProject(index)} icon={faPen} />
                                {/* Use handleViewProject function directly */}
                                <Link to='/todos'> <FontAwesomeIcon className='view-icon' onClick={() => handleViewProject(index)} icon={faEye} /></Link>
                            </div>
                            {/* Render editing UI if currentEdit matches the index */}
                            {currentEdit === index && (
                                <div>
                                    <input
                                        type="text"
                                        value={currentEditedProject.title}
                                        onChange={(e) => setCurrentEditedProject({ ...currentEditedProject, title: e.target.value })}
                                    />
                                    <input
                                        type="text"
                                        value={currentEditedProject.description}
                                        onChange={(e) => setCurrentEditedProject({ ...currentEditedProject, description: e.target.value })}
                                    />
                                    <button onClick={handleUpdateProject}>Update</button>
                                </div>
                            )}
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default Project;
