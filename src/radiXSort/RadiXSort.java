package radiXSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RadiXSort {

    private static String rellenarConCeros(int max, String numeros) {
        //Repito los 0 tantas veces como "diferencia" haya entre la cant de caracteres actuales y la del string max
        return "0".repeat(max - numeros.length()).concat(numeros);
    }
    private static int mayorCantidadCaracteresEnArray(String[] strArray) {
        int max = 0;
        for( String stringDeNumeros: strArray)
            if( stringDeNumeros.length() > max)
                max = stringDeNumeros.length();

        return max;
    }
    public static String[] ArrayDeIntAArrayDeString(int[] intArray)
    {
        String strArray[] = Arrays.stream(intArray)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        return strArray;
    }

    //Devuelve un array de string normalizados de la manera 004, 028, 132, 003, etc...
    public static String[] normalizarArray(String[] strArray) {

        int max = mayorCantidadCaracteresEnArray(strArray);

        ArrayList<String> nuevoElemento =new ArrayList<String>();

        for (String numeros: strArray) {

            String numerosNormalizados = rellenarConCeros(max, numeros);
            nuevoElemento.add(numerosNormalizados);
        }

        String [] arr= new String[nuevoElemento.size()];
        arr=nuevoElemento.toArray(arr);
        return arr;

    }

    private static HashMap<String, ArrayList<String>> crearHashMap(String[] stringSinNormalizado, int numeroIteracion) {

        String[] stringNormalizado = normalizarArray(stringSinNormalizado);
        HashMap<String, ArrayList<String>> diccionario = new HashMap<String, ArrayList<String>>();

        //Actualizo  el hashmap
        for (String numeros: stringNormalizado)
        {
            String caracterAAnalizar = String.valueOf(numeros.charAt(numeros.length() - numeroIteracion));

            ArrayList<String> nuevoElemento =new ArrayList<String>();

            if ( diccionario.get(caracterAAnalizar) == null)
            {
                nuevoElemento.add(numeros);
            }
            else
            {
                diccionario.get(caracterAAnalizar).add(numeros);
                nuevoElemento=diccionario.get(caracterAAnalizar);
            }

            //Poco a poco  llenamos el hashmap
            diccionario.put(caracterAAnalizar, nuevoElemento);
        }
        //Prints para visualizar el proceso
        /*System.out.println("--------Termino la iteracion-------");

        for(String key: diccionario.keySet())
            System.out.println("keys: "+ key +" , values asociados" +diccionario.get(key));
        */

        return diccionario;

    }

    //Devuelvo EL ARRAY REARMADO
    private static String[] convertirDiccionarioAArrayDeStrings (HashMap<String, ArrayList<String>> diccionario)
    {
        ArrayList<String> arrayADevolver =new ArrayList<String>();

        for( String key: diccionario.keySet())
        {
            //vamos armando el array list con todos los values concatenados
            arrayADevolver.addAll(diccionario.get(key));
        }
        //Armamos el array de string
        String [] arr= new String[arrayADevolver.size()];
        arr=arrayADevolver.toArray(arr);

        return arr;

    }


    public static void main(String[] args) {

        int[] intArray = { 4, 28, 132, 3, 61, 5, 43, 19, 76, 88, 97, 25, 12, 435, 67, 55, 17, 54, 89, 32, 16 };

        String[] arrayDeStrings= ArrayDeIntAArrayDeString(intArray);

        HashMap<String, ArrayList<String>> resultados= new HashMap<String, ArrayList<String>>();

        //Invocamos n veces la función crearHasmap, siendo n la mayor cant de caracteres
        for(int i=0; i<mayorCantidadCaracteresEnArray(arrayDeStrings); i++)
        {
            resultados= crearHashMap(arrayDeStrings,i+1 );
            arrayDeStrings= convertirDiccionarioAArrayDeStrings(resultados);
        }

        //Visualizamos los elementos ordenados
        for( String elemento: arrayDeStrings) System.out.println(elemento);

    }
}
