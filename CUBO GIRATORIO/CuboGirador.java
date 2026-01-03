public class CuboGirador {

    public static char[][] pantalla = new char[44][249];
    public static Vector3[] verticesCubo = {
            new Vector3(-1, -1, -1), //0
            new Vector3(-1, 1, -1), //1
            new Vector3(1, 1, -1), //2
            new Vector3(1, -1, -1), //3
            new Vector3(1, 1, 1),  //4
            new Vector3(1, -1, 1),  //5
            new Vector3(-1, -1, 1),  //6
            new Vector3(-1, 1, 1),  //7
    };
    //Esta matriz lia un poco, estoy dividiendo las caras del cubo en 12 triángulos, y cada uno de estos tiene 3 vertices.
    public static int[][] triangulosCubo = {
            //cara de adelante del cubo
            {0, 1, 2},
            {0, 2, 3},
            //la de la derecha
            {3, 2, 4},
            {3, 4, 5},
            //atrás
            {5, 4, 7},
            {5, 7, 6},
            //izquierda
            {6, 7, 1},
            {6, 1, 0},
            //arriba
            {6, 0, 3},
            {6, 3, 5},
            //abajo
            {1, 7, 4},
            {1, 4, 2},
    };


    public static void main(String[] args) {
        inicio();
        double rx = 0.0;
        double ry = 0.0;
        double rz = 0.0;

        while (true) {
            vaciarPantalla();
            limpiarPantalla();
            
            dibujarCubo(rx, ry, rz);
            imprimir();
            rx += 0.01;
            rz += 0.01;
            ry += 0.01;

        }

    }

    public static void inicio() {
        for (int i = 0; i < pantalla.length; i++) {
            for (int j = 0; j < pantalla[0].length; j++) {
                pantalla[i][j] = ' ';
            }
        }
    }

    public static void imprimir() {
        StringBuilder builder = new StringBuilder();
        for (char[] chars : pantalla) {
            for (int j = 0; j < pantalla[0].length; j++) {
                builder.append(chars[j]);
            }
            builder.append("\n");
        }

        System.out.println(builder);
    }
    public static String SIMBOLOS = "@@##~~€€¬¬OO**++";
    public static int ESCALADO = 60;

    public static void dibujarCubo(double rx, double ry, double rz) {
        //Aqui se cambia lo que hay en la variable pantalla

        for (int i = 0; i < triangulosCubo.length; i++) {
            Vector3[] verticesTransformados = new Vector3[3];
            for (int j = 0; j < triangulosCubo[0].length; j++) {
                //AQUI HAREMOS TODAS LAS TRANSFORMACIONES DE LOS VERTICES (ROTAR, METERLO PA DENTRO Y ESCALARLO)
                verticesTransformados[j] =  new Vector3(
                        verticesCubo[triangulosCubo[i][j]].x,
                        verticesCubo[triangulosCubo[i][j]].y,
                        verticesCubo[triangulosCubo[i][j]].z
                );
                //ROTAMOS TODO JODER TODO
                rotarAlrededorDeX(verticesTransformados[j], rx);
                rotarAlrededorDeY(verticesTransformados[j], ry);
                rotarAlrededorDeZ(verticesTransformados[j], rz);


                //PA METERLO DENTRO DE LA PANTALLA AUMENTAMOS EL VALOR DEL EJE Z
                verticesTransformados[j].z += 8;
                //ESCALARLO
                verticesTransformados[j].y *= ESCALADO;
                verticesTransformados[j].x *= ESCALADO * 3;
            }
            Vector2[] puntosProyectados = new Vector2[3];
            for (int j = 0; j < 3; j++) {
                puntosProyectados[j] = proyectar(verticesTransformados[j]);
            }
            //AHORA DIBUJAMOS EL TRIANGULO
            dibujarTriangulo(puntosProyectados[0], puntosProyectados[1], puntosProyectados[2], SIMBOLOS.charAt(i));
        }
    }
    //MULTIPLICAMOS EL VECTOR POR LA MATRIZ DE ROTACION OU YEAH
    public static void rotarAlrededorDeY(Vector3 vector3, double angulo){
        float x,z;
        x = (float) (Math.cos(angulo) * vector3.x  + Math.sin(angulo)* vector3.z);
        z = (float) (-(Math.sin(angulo) * vector3.x)  + Math.cos(angulo)* vector3.z);
        vector3.x = x;
        vector3.z = z;
    }
    public static void rotarAlrededorDeX(Vector3 vector3, double angulo){
        float y, z;
        y = (float) (Math.cos(angulo) * vector3.y  - Math.sin(angulo)* vector3.z);
        z = (float) (Math.sin(angulo) * vector3.y  + Math.cos(angulo)* vector3.z);
        vector3.y = y;
        vector3.z = z;
    }
    public static void rotarAlrededorDeZ(Vector3 vector3, double angulo){
        float x, y;
        x = (float) (Math.cos(angulo) * vector3.x  - Math.sin(angulo)* vector3.y);
        y = (float) (Math.sin(angulo) * vector3.x  + Math.cos(angulo)* vector3.y);
        vector3.x = x;
        vector3.y = y;
    }

    public static Vector2 proyectar(Vector3 vector3) {
        //Aqui aplicamos el teorema de tales ese de los triangulos q se parecen
        return new Vector2(                 //Esta parte es pa moverlo al centro
                Math.round(vector3.x/ vector3.z + (float) (pantalla[0].length/2)),
                Math.round(vector3.y / vector3.z + (float) (pantalla.length/ 2))
        );
    }

    public static void dibujarTrianguloBajoRecto(Vector2 t, Vector2 b0, Vector2 b1, char simbolo) {
        float xb = t.x;
        float xe = t.x;

        final float X_DEC_0 = (t.x - b0.x) / (b0.y - t.y);
        final float X_DEC_1 = (t.x - b1.x) / (b1.y - t.y);

        int y_b = (int) t.y;
        int y_e = (int) (b0.y + 1);
        for (int i = y_b; i < y_e; i++) {
            dibujarLineaEscaneada(i, Math.round(xb), Math.round(xe), simbolo);
            xb -= X_DEC_0;
            xe -= X_DEC_1;
        }

    }
    public static void dibujarTrianguloAltoRecto(Vector2 t0, Vector2 t1, Vector2 b, char simbolo){
        float xb = t0.x;
        float xe = t1.x;

        final float X_INC_0 = (b.x - t0.x) / (b.y - t0.y);
        final float X_INC_1 = (b.x - t1.x) / (b.y - t1.y);

        int y_b = (int) t0.y;
        int y_e = (int) (b.y + 1);
        for (int i = y_b; i < y_e; i++) {
            dibujarLineaEscaneada(i, Math.round(xb), Math.round(xe), simbolo);
            xb += X_INC_0;
            xe += X_INC_1;
        }
    }

    public static void dibujarLineaEscaneada(int y, int x0, int x1, char simbolo) {
        int left = Math.min(x0, x1);
        int right = Math.max(x0, x1);

        for (int i = left; i < right; i++) {
            pantalla[y][i] = simbolo;
        }

    }

    public static void dibujarTriangulo(Vector2 vec0, Vector2 vec1, Vector2 vec2, char simbolo) {
        Vector2 v0 = vec0;
        Vector2 v1 = vec1;
        Vector2 v2 = vec2;

        // sort vertices by ASC y
        if (v0.y > v1.y) {
            Vector2 tmp = v0;
            v0 = v1;
            v1 = tmp;
        }

        if (v1.y > v2.y) {
            Vector2 tmp = v1;
            v1 = v2;
            v2 = tmp;
        }

        if (v0.y > v1.y) {
            Vector2 tmp = v0;
            v0 = v1;
            v1 = tmp;
        }

        if (v2.y == v1.y) {
            dibujarTrianguloBajoRecto(v0, v1, v2, simbolo);
            return;
        }

        if (v0.y == v1.y) {
            dibujarTrianguloAltoRecto(v0, v1, v2, simbolo);
            return;
        }

        // midpoint
        float alpha = (v1.y - v0.y) / (v2.y - v0.y);
        Vector2 midpoint = new Vector2(
                v0.x + (v2.x - v0.x) * alpha,
                v1.y
        );

        dibujarTrianguloBajoRecto(v0, v1, midpoint, simbolo);
        dibujarTrianguloAltoRecto(v1, midpoint, v2, simbolo);
    }

    //Esto me lo ha dicho el chatgpt pa limpiar la pantalla
    public static void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("No se pudo limpiar la pantalla");
        }
    }
    public static void vaciarPantalla(){
        for (int i = 0; i < pantalla.length; i++) {
            for (int j = 0; j < pantalla[0].length; j++) {
                pantalla[i][j] = ' ';
            }
        }
    }

    public static void esperar() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
