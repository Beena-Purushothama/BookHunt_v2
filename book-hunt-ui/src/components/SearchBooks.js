import React, { Component } from "react";
import SearchBar from "./SearchBar";
import FilteredBooks from "./FilteredBooks";

class SearchBooks extends Component {
  state = {
    searchText: ""
  };

  onSearchTextChange = value => {
    this.setState({ searchText: value });
  };

  render() {
    return (
      <div className="">
        <SearchBar onSearchTextChange={this.onSearchTextChange} />
        <FilteredBooks
          searchText={this.state.searchText}
          page={this.state.page}
        />
      </div>
    );
  }
}

export default SearchBooks;
