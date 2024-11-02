package com.tm.foodsv.util;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class PageDTO<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private boolean last;
    private int size;
    private int number;
    private SortDTO sort;
    private boolean first;
    private int numberOfElements;
    private boolean empty;

    // Clase interna para Sort
    public static class SortDTO {
        private boolean sorted;
        private boolean unsorted;
        private boolean empty;

        // Constructor
        public SortDTO(Sort sort) {
            this.sorted = sort.isSorted();
            this.unsorted = !sort.isSorted();
            this.empty = sort.isEmpty();
        }

		public boolean isSorted() {
			return sorted;
		}

		public void setSorted(boolean sorted) {
			this.sorted = sorted;
		}

		public boolean isUnsorted() {
			return unsorted;
		}

		public void setUnsorted(boolean unsorted) {
			this.unsorted = unsorted;
		}

		public boolean isEmpty() {
			return empty;
		}

		public void setEmpty(boolean empty) {
			this.empty = empty;
		}


    }

    // Constructor que convierte Page<T> a CustomPageDTO<T>
    public PageDTO(Page<T> page) {
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.last = page.isLast();
        this.size = page.getSize();
        this.number = page.getNumber();
        this.sort = new SortDTO(page.getSort());
        this.first = page.isFirst();
        this.numberOfElements = page.getNumberOfElements();
        this.empty = page.isEmpty();
    }

    // Getters y setters para todos los campos
    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public SortDTO getSort() {
        return sort;
    }

    public void setSort(SortDTO sort) {
        this.sort = sort;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
