select count(id)
from users; -- actual

select count(distinct id)
from users;
-- expected


-- ==US2==
SELECT COUNT(*)
FROM book_borrow
WHERE is_returned = 0;

-- ==US3==
SELECT NAME
FROM book_categories;

-- ==US4==
select b.name as bookName, b.isbn, b.year, b.author, bc.name as categoryName, b.description
from books b
         join book_categories bc on bc.id = b.book_category_id
where b.name = 'Clean Code'
order by b.isbn desc;  -- THIS IS THE QUERY!!  ISBN DESC BC IN UI PART ISBN DESC AS DEFAULT.

select *
from books
where isbn = 999239923;

select *
from books
where name = 'Clean Code'
order by isbn desc;


-- ==US5==

select bc.name, count(*)
from book_borrow bb
         join books b on b.id = bb.book_id
         join book_categories bc on bc.id = b.book_category_id
group by name
order by 2 desc;

-- ==US6==
SELECT b.NAME as bookName, ISBN, YEAR, AUTHOR, bc.name as categoryName
FROM books b
         JOIN book_categories bc on bc.id = b.book_category_id
order by added_date desc;

select *
from books;


select id, name, author
from books
where name = 'Clean Code'
  and author = 'Robert C.Martin'
order by id desc;

