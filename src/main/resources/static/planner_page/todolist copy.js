const todoInputElem = document.querySelector('.todo-input');
const todoListElem = document.querySelector('.todo-list');
const todoPlusElem = document.querySelector('.todo-plus');

let todos = [];
let id = 0;

const setTodos = (newTodos) => {
    todos = newTodos;
}

const getTodos = (currentTodos) => {
    todos = currentTodos;
}

const getAllTodos = () => {
    return todos;
}

const appendTodos = (text) => {
    const newId = id++;
    const newTodos = getAllTodos().concat({id: newId, content: text })
    // const newTodos = [...getAllTodos(), {id: newId, isCompleted: false, content: text }]
    setTodos(newTodos);
    paintTodos();
}

const writeTodos = (text) => {
    // const newId = id++;
    const currentTodos = getAllTodos();
    // const newTodos = getAllTodos().concat({id: newId, content: text })
    // // const newTodos = [...getAllTodos(), {id: newId, isCompleted: false, content: text }]
    setTodos(currentTodos);
    paintTodos();
}

const deleteTodo = (todoId) => {
    const newTodos = getAllTodos().filter(todo => todo.id !== todoId );
    setTodos(newTodos);
    paintTodos()
}

// const completeTodo = (todoId) => {
//     const newTodos = getAllTodos().map(todo => todo.id === todoId ? {...todo,  isCompleted: !todo.isCompleted} : todo )
//     setTodos(newTodos);
//     paintTodos();
// }

const updateTodo = (text, todoId) => {
    const currentTodos = getAllTodos();
    const newTodos = currentTodos.map(todo => todo.id === todoId ? ({...todo, content: text}) : todo);
    setTodos(newTodos);
    paintTodos();
}

const onDbclickTodo = (e, todoId) => {
    const todoElem = e.target;
    const inputText = e.target.innerText;
    const todoItemElem = todoElem.parentNode;
    const inputElem = document.createElement('input');
    inputElem.value = inputText;
    inputElem.classList.add('edit-input');
    inputElem.addEventListener('keypress', (e)=>{
        if(e.key === 'Enter') {
            updateTodo(e.target.value, todoId);
            document.body.removeEventListener('click', onClickBody );
        }
    })

    const onClickBody = (e) => {
        if(e.target !== inputElem)  {
            todoItemElem.removeChild(inputElem);
            document.body.removeEventListener('click', onClickBody );
        }
    }
    
    document.body.addEventListener('click', onClickBody)
    todoItemElem.appendChild(inputElem);
}

const paintTodos = () => {
    todoListElem.innerHTML = null; //todoListElem 요소 안의 HTML 초기화
	const allTodos = getAllTodos() // todos 배열 가져오기

    allTodos.forEach(todo => { 
        const todoItemElem = document.createElement('li');
        todoItemElem.classList.add('todo-item');

        todoItemElem.setAttribute('data-id', todo.id );

        // const checkboxElem = document.createElement('div');
        // checkboxElem.classList.add('checkbox');
        // checkboxElem.addEventListener('click', () => completeTodo(todo.id))

        const img1= document.createElement("img");
        img1.classList.add("todo-timeimg");
        img1.src = "/Users/dajin/js-entrip/img/planner_page/time.png";

        const starttimeElem = document.createElement('INPUT');
        starttimeElem.classList.add('todo-time');
        starttimeElem.setAttribute("type", "time");

        const endtimeElem = document.createElement('INPUT');
        endtimeElem.classList.add('todo-time');
        endtimeElem.setAttribute("type", "time");        

        const img2 = document.createElement("img");
        img2.classList.add('todo-inputimg');
        img2.src = "/Users/dajin/js-entrip/img/planner_page/content.png";
 
        const todoElem = document.createElement('div');
        todoElem.classList.add('todo');
        todoElem.addEventListener('dblclick', (event) => onDbclickTodo(event, todo.id))
        todoElem.innerText = todo.content;
    
        const delBtnElem = document.createElement('img');
        delBtnElem.classList.add('delBtn');
        delBtnElem.src = "/Users/dajin/js-entrip/img/planner_page/closebtn.png";
        delBtnElem.addEventListener('click', () =>  deleteTodo(todo.id))

        todoItemElem.appendChild(img1);
        todoItemElem.appendChild(starttimeElem);
        todoItemElem.appendChild(endtimeElem);
        todoItemElem.appendChild(img2);
        todoItemElem.appendChild(todoElem);
        todoItemElem.appendChild(delBtnElem);
        todoListElem.appendChild(todoItemElem);
    })
}

const init = () => {
    todoPlusElem.addEventListener('click', (e) =>{
        // console.log('click');
        
        // if( e.key === 'click' ){
            appendTodos(e.target.value); 
            todoInputElem.value ='';
        // }
    })

    todoInputElem.addEventListener('keypress', (e) => {
        if(e.key === 'Enter') {
            writeTodos(e.target.value); 
            // todoInputElem.value=e.target.value;
        }
    })
}

init()