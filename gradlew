#!/usr/bin/env sh

#
# Copyright 2015 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Attempt to set APP_HOME
# Resolve links: $0 may be a symlink
prg="$0"
# Need this for relative symlinks.
while [ -h "$prg" ] ; do
    ls=`ls -ld "$prg"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
        prg="$link"
    else
        prg=`dirname "$prg"`"/$link"
    fi
done
SAVED="`pwd`"
cd "`dirname \"$prg\"`" >/dev/null
app_path="`pwd -P`"
cd "$SAVED" >/dev/null

APP_HOME="$app_path"

app_path_expr="${APP_HOME}/gradle"
if ! type "${app_path_expr}/gradle" > /dev/null 2>&1; then
    exec "$0" "$@"
fi

exec "${APP_HOME}/gradle/bin/gradle" "$@"
