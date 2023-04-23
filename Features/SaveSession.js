function getResultFromRequest(request) {
    return Promise.resolve(request.result);
}

async function getDB() {
    const request = window.indexedDB.open("wawc");
    try {
        const db = await getResultFromRequest(request);
        return db;
    } catch (error) {
        console.error("Erro ao abrir o banco de dados:", error);
        throw error;
    }
}

async function readAllRecords() {
    const db = await getDB();
    const objectStore = db.transaction("user").objectStore("user");
    const request = objectStore.getAll();
    try {
        const records = await getResultFromRequest(request);
        return records;
    } catch (error) {
        console.error("Erro ao ler os registros:", error);
        throw error;
    }
}

async function writeSessionToFile() {
    try {
        const records = await readAllRecords();
        const recordsJson = JSON.stringify(records);

        // Cria um novo arquivo de texto com o nome "session.txt"
        window.requestFileSystem = window.requestFileSystem || window.webkitRequestFileSystem;
        window.requestFileSystem(window.TEMPORARY, 1024 * 1024, function(fs) {
            fs.root.getFile("session.txt", {create: true}, function(fileEntry) {
                fileEntry.createWriter(function(fileWriter) {
                    fileWriter.onwriteend = function(e) {
                        console.log("Dados da sessão foram escritos no arquivo!");
                    };

                    // Escreve os dados da sessão no arquivo
                    const blob = new Blob([recordsJson], {type: "text/plain"});
                    fileWriter.write(blob);
                }, function(e) {
                    console.error("Erro ao criar o escritor do arquivo:", e);
                    throw e;
                });
            }, function(e) {
                console.error("Erro ao criar o arquivo:", e);
                throw e;
            });
        }, function(e) {
            console.error("Erro ao solicitar o sistema de arquivos:", e);
            throw e;
        });
    } catch (error) {
        console.error("Erro ao gravar a sessão em arquivo:", error);
        throw error;
    }
}

writeSessionToFile();
