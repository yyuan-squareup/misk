# Contributing

If you would like to contribute code to this project you can do so through GitHub by
forking the repository and sending a pull request.

When submitting code, please make every effort to follow existing conventions
and style in order to keep the code as readable as possible.

Before your code can be accepted into the project you must also sign the
[Individual Contributor License Agreement (CLA)][1].

## Building Misk locally

Install and activate hermit: https://cashapp.github.io/hermit/

Use gradle to run all Kotlin tests locally:

```shell
gradle build
```

misk-hibernate tests expect a mysql server running on `localhost:3306` with no password set on
the root user. You might stand up a server with a docker image, e.g.

```shell
docker run -d --rm --name "mysql-57" -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=true -e MYSQL_LOG_CONSOLE=true mysql:5.7 --sql-mode=""
```

Misk may download these Docker images as part of its tests. Because tests can time out, pre-downloading these can help resolve timeouts.

```
alpine:latest
amazon/dynamodb-local:latest
cockroachdb/cockroach
gcr.io/cloud-spanner-emulator/emulator
pingcap/tidb
postgres
redis:6.2-alpine
softwaremill/elasticmq
vitess/base
```

## Breaking changes

We use the [Kotlin binary compatibility validator][2] to check for API changes. If 
a change contains an API change and breaks the build, run the `:apiDump` task and 
commit the resulting changes to the `.api` files. `.api` files should not have 
removals and additions in the same change so that downstream apps do not immediately
run into backwards-compatibility issues.

 [1]: https://spreadsheets.google.com/spreadsheet/viewform?formkey=dDViT2xzUHAwRkI3X3k5Z0lQM091OGc6MQ&ndplr=1
 [2]: https://github.com/Kotlin/binary-compatibility-validator
