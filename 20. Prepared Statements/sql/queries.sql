explain (costs false) select s.first_name, s.last_name, c.title
                      from student s
                               inner join student_course sc on s.id = sc.student_id
                               inner join course c on sc.course_id = c.id
                               inner join lesson l on c.id = l.course_id
                      where l.name = 'Simple Math';

prepare get_students_by_lesson(char) as select s.first_name, s.last_name, c.title
                                        from student s
                                                 inner join student_course sc on s.id = sc.student_id
                                                 inner join course c on sc.course_id = c.id
                                                 inner join lesson l on c.id = l.course_id
                                        where l.name = $1;

explain execute  get_students_by_lesson('Simple Math');

select * from pg_prepared_statements;

