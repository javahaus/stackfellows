# Database Schema

## Schema Overview

We developed the site to use PostgresQL by default, but any relational SQL DB can be leverage with a little effort.

This is a living document until we deliver at the end of this sprint.

![Database Schema Diagram](../Imgs/stackfellows-db-schema.jpg)

## Entities

AppUser: Represents a registered user.

Post: Represents a Post to the "Question Blog" portion of the size. AppUser can make many of these.

Comment: Represents a Comment made on a Post. AppUser can make many of these. Posts might have zero to infinite number of theses. 

## Data Relationships Design

![Relational Data Model](../Imgs/stackfellows-relational-data-design.jpg)

## CRUD Operations

[ ] Create: Post a new question/blogpost, post a new comment, newly registered user.

[ ] Read: Get a specific blogbost, many blogposts by user, all blogposts, or get one or more comments related to a post.

[ ] Update: Edit an existing blogpost, and change the number of "up votes" on a post or comment, registered user's own information. 

[ ] Delete: Remove one's own blogpost or comment.
