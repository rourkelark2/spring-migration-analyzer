/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.migrationanalyzer.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public final class IoUtilsTests {

    @Test
    public void create() throws IOException {
        File file = new File("build/dir");
        delete(file);

        IoUtils.createDirectoryIfNecessary(file);
        assertTrue(file.isDirectory());
    }

    @Test
    public void createWhenDirectoryAlreadyExists() throws IOException {
        File file = new File("build/directory");
        if (!file.isDirectory()) {
            assertTrue(file.mkdirs());
        }

        IoUtils.createDirectoryIfNecessary(file);
        assertTrue(file.isDirectory());
    }

    @Test(expected = IOException.class)
    public void createFailsIfAlreadyExistsAsFile() throws IOException {
        File file = new File("build/file");
        delete(file);

        try {
            assertTrue(file.createNewFile());
        } catch (IOException ioe) {
            fail();
        }

        IoUtils.createDirectoryIfNecessary(file);
    }

    private void delete(File file) {
        if (file.exists()) {
            assertTrue(file.delete());
        }
    }
}
