# hb-eager-vs-lazy-demo
Hibernate app that uses JOIN FETCH query to access a related db object after session close

The real changes are in these 2 files:
1. EagerLazyDemo.java - closes the hibernate session before accessing instructor's courses, and uses query parameter with JOIN FETCH.
2. Instructor.java - implements @OneToMany on courses with FetchType.LAZY
