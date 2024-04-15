import './App.css';
import {Component} from "react";
import {BrowserRouter as Router, Route, Routes, Navigate} from "react-router-dom";
import bookShopService from "../../repository/bookShopRepository";
import Books from "../Books/BookList/books";
import Header from "../Header/header";
import Categories from "../Categories/categories";


class App extends Component{
    constructor(props) {
        super(props);
        this.state = {
            books: [],
            categories: []
        }
    }

    render() {
        return(
            <Router>
                <Header/>
                <main>
                    <div className={"container"}>
                        <Routes>
                            <Route path="books" element={<Books books={this.state.books}
                                                                onDelete={this.deleteBook}/>} />
                            <Route path="categories" element={<Categories categories={this.state.categories}/>} />
                            <Route path="*" element={<Navigate to="/books" />} />
                        </Routes>
                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.loadBooks();
        this.loadCategories()
    }

    loadBooks = () => {
        bookShopService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            })
            .catch((error) => {
                console.log('Error fetching books: ', error)
            });
    }

    loadCategories = () => {
        bookShopService.fetchCategories()
            .then((data) =>{
                this.setState({
                    categories: data.data
                })
            });
    }

    deleteBook = (id) => {
        bookShopService.deleteBook(id)
            .then(() =>{
                this.loadBooks();
            })
    }

}

export default App;
