<?php
require_once 'config.php';

// Get HTTP method
$method = $_SERVER['REQUEST_METHOD'];

// Handle preflight requests
if ($method == 'OPTIONS') {
    http_response_code(200);
    exit();
}

// Get request URI
$request_uri = $_SERVER['REQUEST_URI'];
$path = parse_url($request_uri, PHP_URL_PATH);
$path_parts = explode('/', trim($path, '/'));

// Route handling
switch ($method) {
    case 'GET':
        if (isset($_GET['action'])) {
            switch ($_GET['action']) {
                case 'getAll':
                    getAllBarang();
                    break;
                case 'getById':
                    getBarangById();
                    break;
                case 'search':
                    searchBarang();
                    break;
                default:
                    getAllBarang();
                    break;
            }
        } else {
            getAllBarang();
        }
        break;
    
    case 'POST':
        addBarang();
        break;
    
    case 'PUT':
        updateBarang();
        break;
    
    case 'DELETE':
        deleteBarang();
        break;
    
    default:
        sendResponse(false, null, 'Method not allowed');
        break;
}

// Get all barang
function getAllBarang() {
    global $pdo;
    
    try {
        $sql = "SELECT * FROM tb_barang ORDER BY kdbarang ASC";
        $stmt = $pdo->prepare($sql);
        $stmt->execute();
        
        $barang = $stmt->fetchAll();
        
        // Convert numeric strings to integers
        foreach ($barang as &$item) {
            $item['harga'] = (int)$item['harga'];
            $item['stok'] = (int)$item['stok'];
        }
        
        sendResponse(true, $barang, 'Data retrieved successfully');
        
    } catch (PDOException $e) {
        sendResponse(false, null, 'Error: ' . $e->getMessage());
    }
}

// Get barang by ID
function getBarangById() {
    global $pdo;
    
    if (!isset($_GET['kdbarang'])) {
        sendResponse(false, null, 'Parameter kdbarang is required');
    }
    
    try {
        $sql = "SELECT * FROM tb_barang WHERE kdbarang = :kdbarang";
        $stmt = $pdo->prepare($sql);
        $stmt->bindParam(':kdbarang', $_GET['kdbarang']);
        $stmt->execute();
        
        $barang = $stmt->fetch();
        
        if ($barang) {
            // Convert numeric strings to integers
            $barang['harga'] = (int)$barang['harga'];
            $barang['stok'] = (int)$barang['stok'];
            
            sendResponse(true, [$barang], 'Data found');
        } else {
            sendResponse(false, null, 'Data not found');
        }
        
    } catch (PDOException $e) {
        sendResponse(false, null, 'Error: ' . $e->getMessage());
    }
}

// Search barang
function searchBarang() {
    global $pdo;
    
    if (!isset($_GET['query'])) {
        sendResponse(false, null, 'Parameter query is required');
    }
    
    try {
        $query = '%' . $_GET['query'] . '%';
        $sql = "SELECT * FROM tb_barang WHERE nmbarang LIKE :query OR deskripsi LIKE :query ORDER BY kdbarang ASC";
        $stmt = $pdo->prepare($sql);
        $stmt->bindParam(':query', $query);
        $stmt->execute();
        
        $barang = $stmt->fetchAll();
        
        // Convert numeric strings to integers
        foreach ($barang as &$item) {
            $item['harga'] = (int)$item['harga'];
            $item['stok'] = (int)$item['stok'];
        }
        
        sendResponse(true, $barang, 'Search completed');
        
    } catch (PDOException $e) {
        sendResponse(false, null, 'Error: ' . $e->getMessage());
    }
}

// Add new barang
function addBarang() {
    global $pdo;
    
    $input = json_decode(file_get_contents('php://input'), true);
    
    if (!$input) {
        sendResponse(false, null, 'Invalid JSON data');
    }
    
    $required_fields = ['kdbarang', 'nmbarang', 'harga', 'stok', 'deskripsi', 'foto'];
    
    foreach ($required_fields as $field) {
        if (!isset($input[$field])) {
            sendResponse(false, null, "Field $field is required");
        }
    }
    
    try {
        $sql = "INSERT INTO tb_barang (kdbarang, nmbarang, harga, stok, deskripsi, foto) 
                VALUES (:kdbarang, :nmbarang, :harga, :stok, :deskripsi, :foto)";
        
        $stmt = $pdo->prepare($sql);
        $stmt->bindParam(':kdbarang', $input['kdbarang']);
        $stmt->bindParam(':nmbarang', $input['nmbarang']);
        $stmt->bindParam(':harga', $input['harga']);
        $stmt->bindParam(':stok', $input['stok']);
        $stmt->bindParam(':deskripsi', $input['deskripsi']);
        $stmt->bindParam(':foto', $input['foto']);
        
        $stmt->execute();
        
        sendResponse(true, null, 'Data added successfully');
        
    } catch (PDOException $e) {
        sendResponse(false, null, 'Error: ' . $e->getMessage());
    }
}

// Update barang
function updateBarang() {
    global $pdo;
    
    $input = json_decode(file_get_contents('php://input'), true);
    
    if (!$input) {
        sendResponse(false, null, 'Invalid JSON data');
    }
    
    if (!isset($input['kdbarang'])) {
        sendResponse(false, null, 'Field kdbarang is required');
    }
    
    try {
        $sql = "UPDATE tb_barang SET nmbarang = :nmbarang, harga = :harga, stok = :stok, 
                deskripsi = :deskripsi, foto = :foto WHERE kdbarang = :kdbarang";
        
        $stmt = $pdo->prepare($sql);
        $stmt->bindParam(':kdbarang', $input['kdbarang']);
        $stmt->bindParam(':nmbarang', $input['nmbarang']);
        $stmt->bindParam(':harga', $input['harga']);
        $stmt->bindParam(':stok', $input['stok']);
        $stmt->bindParam(':deskripsi', $input['deskripsi']);
        $stmt->bindParam(':foto', $input['foto']);
        
        $stmt->execute();
        
        if ($stmt->rowCount() > 0) {
            sendResponse(true, null, 'Data updated successfully');
        } else {
            sendResponse(false, null, 'No data updated or data not found');
        }
        
    } catch (PDOException $e) {
        sendResponse(false, null, 'Error: ' . $e->getMessage());
    }
}

// Delete barang
function deleteBarang() {
    global $pdo;
    
    if (!isset($_GET['kdbarang'])) {
        sendResponse(false, null, 'Parameter kdbarang is required');
    }
    
    try {
        $sql = "DELETE FROM tb_barang WHERE kdbarang = :kdbarang";
        $stmt = $pdo->prepare($sql);
        $stmt->bindParam(':kdbarang', $_GET['kdbarang']);
        
        $stmt->execute();
        
        if ($stmt->rowCount() > 0) {
            sendResponse(true, null, 'Data deleted successfully');
        } else {
            sendResponse(false, null, 'Data not found');
        }
        
    } catch (PDOException $e) {
        sendResponse(false, null, 'Error: ' . $e->getMessage());
    }
}
?>