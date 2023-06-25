import axios from "axios";
import { booksAPIRootPath, notesAPIRootPath } from "./configuration/config";
import { Book, Note } from "./models/types";
import { NavigateFunction } from "react-router-dom";

export type ApiClient = {
    getBookById: (id: string, navigate: NavigateFunction) => Promise<Book>;
    getBooks: (page: number, pageSize: number, navigate: NavigateFunction) => Promise<Book[]>;
    getRecommendedBooks: (count: number, navigate: NavigateFunction) => Promise<Book[]>;
    getDailyBook: (navigate: NavigateFunction) => Promise<Book>;
    getTotalBooksCount: (navigate: NavigateFunction) => Promise<number>;
    getLatestNotes: (count: number, navigate: NavigateFunction) => Promise<Note[]>;
}

const checkAxiosErrorAndNavigate = (err: any, navigate: NavigateFunction) => {
    if (axios.isAxiosError(err)){
        navigate("/error");
    }
}

export const createApiClient = (): ApiClient => {
    return {
        getBookById: (id: string, navigate: NavigateFunction) => {
            return axios.get(`${booksAPIRootPath}book?id=${id}`,
                                {headers: {"Access-Control-Allow-Origin": "*"}})
                        .then((res) => res.data)
                        .catch((err) => {
                            checkAxiosErrorAndNavigate(err, navigate);
                            console.log(err);
                        });
        },
        getBooks: (page: number, pageSize: number, navigate: NavigateFunction) => {
            return axios.get(`${booksAPIRootPath}books?pageNum=${page}&pageSize=${pageSize}`,
                                {headers: {"Access-Control-Allow-Origin": "*"}})
                        .then((res) => res.data)
                        .catch((err) => {
                            checkAxiosErrorAndNavigate(err, navigate);
                            console.log(err);
                        });
        },
        getRecommendedBooks: (count: number, navigate: NavigateFunction) => {
            return axios.get(`${booksAPIRootPath}recommended?count=${count}`,
                                {headers: {"Access-Control-Allow-Origin": "*"}})
                        .then((res) => res.data)
                        .catch((err) => {
                            checkAxiosErrorAndNavigate(err, navigate);
                            console.log(err);
                        });
        },
        getDailyBook: (navigate: NavigateFunction) => {
            return axios.get(`${booksAPIRootPath}daily`,
                                {headers: {"Access-Control-Allow-Origin": "*"}})
                        .then((res) => res.data)
                        .catch((err) => {
                            checkAxiosErrorAndNavigate(err, navigate);
                            console.log(err);
                        });
        },
        getTotalBooksCount: (navigate: NavigateFunction) => {
            return axios.get(`${booksAPIRootPath}totalBooksCount`,
                                {headers: {"Access-Control-Allow-Origin": "*"}})
                        .then((res) => res.data)
                        .catch((err) => {
                            checkAxiosErrorAndNavigate(err, navigate);
                            console.log(err);
                        });
        },
        getLatestNotes: (count: number, navigate: NavigateFunction) => {
            return axios.get(`${notesAPIRootPath}latest?count=${count}`,
                                {headers: {"Access-Control-Allow-Origin": "*"}})
                        .then((res) => res.data)
                        .catch((err) => {
                            checkAxiosErrorAndNavigate(err, navigate);
                            console.log(err);
                        });
        }
    }
}

