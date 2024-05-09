import React, { useEffect, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash, faCheck, faPen } from '@fortawesome/free-solid-svg-icons';

function Todo() {
    const [isCompleteScreen, setIsCompleteScreen] = useState(false);
    const [allTodos, setTodos] = useState([]);
    const [newTitle, setNewTitle] = useState("");
    const [newDescription, setNewDescription] = useState("");
    const [completedTodos, setCompletedTodos] = useState([]);
    const [currentEdit, setCurrentEdit] = useState(null);
    const [currentEditedItem, setCurrentEditedItem] = useState({ title: "", description: "" });

    const [pendingCount, setPendingCount] = useState(0);
    const [completedCount, setCompletedCount] = useState(0);

    const handleAddTodo = () => {
        let newTodoItem = {
            title: newTitle,
            description: newDescription,
        };

        let updatedTodoArr = [...allTodos];
        updatedTodoArr.push(newTodoItem);
        setTodos(updatedTodoArr);
        localStorage.setItem('todolist', JSON.stringify(updatedTodoArr));
    };

    const handleDeleteTodo = (index) => {
        let reducedTodo = [...allTodos];
        reducedTodo.splice(index, 1);
        localStorage.setItem('todolist', JSON.stringify(reducedTodo));
        setTodos(reducedTodo);
    };

    const handleComplete = (index) => {
        let now = new Date();
        let completedOn = `${now.getDate()}-${now.getMonth() + 1}-${now.getFullYear()} at ${now.getHours()}:${now.getMinutes()}:${now.getSeconds()}`;

        let filteredItem = {
            ...allTodos[index],
            completedOn: completedOn,
        };

        let updatedCompletedArr = [...completedTodos];
        updatedCompletedArr.push(filteredItem);
        setCompletedTodos(updatedCompletedArr);
        handleDeleteTodo(index);
        localStorage.setItem('completedTodos', JSON.stringify(updatedCompletedArr));
    };

    const handleDeleteCompletedTodo = (index) => {
        let reducedTodo = [...completedTodos];
        reducedTodo.splice(index, 1);
        localStorage.setItem('completedTodos', JSON.stringify(reducedTodo));
        setCompletedTodos(reducedTodo);
    };

    const handleEdit = (index) => {
        setCurrentEdit(index);
        setCurrentEditedItem(allTodos[index]);
    };

    const handleUpdateTitle = (title) => {
        setCurrentEditedItem({ ...currentEditedItem, title });
    };

    const handleUpdateDescription = (description) => {
        setCurrentEditedItem({ ...currentEditedItem, description });
    };

    const handleUpdateToDo = () => {
        let updatedTodoArr = [...allTodos];
        updatedTodoArr[currentEdit] = currentEditedItem;
        setTodos(updatedTodoArr);
        localStorage.setItem('todolist', JSON.stringify(updatedTodoArr));
        setCurrentEdit(null); // Reset editing index
    };

    useEffect(() => {
        let savedTodo = JSON.parse(localStorage.getItem('todolist'));
        let savedCompletedTodo = JSON.parse(localStorage.getItem('completedTodos'));
        if (savedTodo) {
            setTodos(savedTodo);
        }
        if (savedCompletedTodo) {
            setCompletedTodos(savedCompletedTodo);
        }
    }, []);

    useEffect(() => {
        // Calculate the number of pending and completed tasks
        setPendingCount(allTodos.filter(todo => !todo.completedOn).length);
        setCompletedCount(completedTodos.length);
    }, [allTodos, completedTodos]);

    return (
        <div className='App'>
            <h1>Project Todo's</h1>
            {/* Summary section */}
            <p>Summary: {pendingCount} pending task{pendingCount !== 1 ? 's' : ''}/{completedCount} completed task{completedCount !== 1 ? 's' : ''}</p>

            <div className='todo-wrapper'>
                <div className='todo-input'>
                    <div className='todo-input-item'>
                        <label>Title</label>
                        <input type='text' value={newTitle} onChange={(e) => setNewTitle(e.target.value)} placeholder='Title for task' />
                    </div>
                    <div className='todo-input-item'>
                        <label>Description</label>
                        <input type='text' value={newDescription} onChange={(e) => setNewDescription(e.target.value)} placeholder='Description for task' />
                    </div>
                    <div className='todo-input-item'>
                        <button type='button' onClick={handleAddTodo} className='primaryButton'>Add</button>
                    </div>
                </div>

                <div className='btn-area'>
                    <button
                        className={`secondaryBtn ${!isCompleteScreen && 'active'}`}
                        onClick={() => setIsCompleteScreen(false)}
                    >
                        Pending
                    </button>
                    <button
                        className={`secondaryBtn ${isCompleteScreen && 'active'}`}
                        onClick={() => setIsCompleteScreen(true)}
                    >
                        Completed
                    </button>
                </div>

                <div className='todo-list'>
                    {isCompleteScreen === false &&
                        allTodos.map((item, index) => {
                            return (
                                <div className="todo-list-item" key={index}>
                                    {currentEdit === index ? (
                                        <div className='edit__wrapper'>
                                            <input
                                                placeholder='Updated Title'
                                                onChange={(e) => handleUpdateTitle(e.target.value)}
                                                value={currentEditedItem.title}
                                            />
                                            <textarea
                                                placeholder='Updated Description'
                                                rows={4}
                                                onChange={(e) => handleUpdateDescription(e.target.value)}
                                                value={currentEditedItem.description}
                                            />
                                            <button
                                                type="button"
                                                onClick={handleUpdateToDo}
                                                className="primaryBtn"
                                            >
                                                Update
                                            </button>
                                        </div>
                                    ) : (
                                        <>
                                            <div>
                                                <h3>{item.title}</h3>
                                                <p>{item.description}</p>
                                            </div>
                                            <div>
                                                <FontAwesomeIcon className='icon' onClick={() => handleDeleteTodo(index)} icon={faTrash} />
                                                <FontAwesomeIcon className='check-icon' onClick={() => handleComplete(index)} icon={faCheck} />
                                                <FontAwesomeIcon className='edit-icon' onClick={() => handleEdit(index)} icon={faPen} />
                                            </div>
                                        </>
                                    )}
                                </div>
                            );
                        })}
                    {isCompleteScreen === true &&
                        completedTodos.map((item, index) => {
                            return (
                                <div className="todo-list-item" key={index}>
                                    <div>
                                        <h3>{item.title}</h3>
                                        <p>{item.description}</p>
                                        <p><small>Completed on: {item.completedOn}</small></p>
                                    </div>
                                    <div>
                                        <FontAwesomeIcon className='icon' onClick={() => handleDeleteCompletedTodo(index)} icon={faTrash} />
                                    </div>
                                </div>
                            );
                        })}
                </div>
            </div>
        </div>
    );
}

export default Todo;
