export default class TodoList {
    constructor(id, title, todoListId = null, todoListItems = []){
        this.id = id;
        this.title = title;
        this.todoListId = todoListId;
        this.todoListItems = todoListItems;
    }
}
