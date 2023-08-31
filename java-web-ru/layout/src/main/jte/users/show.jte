<!-- BEGIN -->
@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <table class="table table-striped">
                <thead>
                <tr>
                    <th class="col-1">ID</th>
                    <th class="col-3">Имя</th>
                    <th class="col-2">Email</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${page.getUser().getId()}</td>
                    <td>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</td>
                    <td>${page.getUser().getEmail()}</td>
                </tr>
                </tbody>
        </table>
    </div>
`
)
<!-- END -->
