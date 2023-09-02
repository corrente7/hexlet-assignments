<!-- BEGIN -->
@import exercise.dto.posts.PostsPage
@param PostsPage page

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>Hello Hexlet!</title>
</head>
<body>
<div class="mx-auto p-4 py-md-5">
    <main>
        <h1>Список пользователей</h1>
        @for(var post : page.getPosts())
            <div>
                <p><a href="/posts/${post.getId()}">${post.getName()}</a></p>
            </div>
        @endfor
    </main>
</div>
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item"><a class="page-link" href="/posts?page=${page.getPageNumber() - 1}">Previous</a></li>
        <li class="page-item"><a class="page-link" href="/posts?page=${page.getPageNumber()}">${page.getPageNumber()}</a></li>
        <li class="page-item"><a class="page-link" href="/posts?page=${page.getPageNumber() + 1}">Next</a></li>
    </ul>
</nav>
</body>
</html>
<!-- END -->
