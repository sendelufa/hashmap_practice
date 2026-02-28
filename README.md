# HashMapEx

Учебный репозиторий, приложение к серии видео про `HashMap` в Java.

- ▶️ [Что такое HashMap?](https://youtu.be/ky6zv6O7VfA)

## Что внутри

### 1) Исследования `HashMap` (демо)
- `ru.sendel.CollisionDemo`  
  Коллизии: сравнение `GoodKey` и `BadKey`, влияние на `put/get`.
- `ru.sendel.TreeifyDemo`  
  Переход бакета в дерево (`HashMap$TreeNode`) при большом числе коллизий.
- `ru.sendel.ResizeSpikeDemo`  
  Скачки времени на `put` во время `resize`, вывод `capacity/threshold`.
- `ru.sendel.MissingKeys`  
  Демонстрация проблемы с изменяемым ключом и работы с `null`-ключом.
- `ru.sendel.CalculateIndex`  
  Примеры `hashCode` и условий коллизий строк.

### 2) Практика
- `ru.sendel.practice.MetricsAggregation`  
  Агрегация метрик через `merge`.
- `ru.sendel.practice.EnumDispatcher`  
  Диспетчеризация обработчиков через `EnumMap`.
- `ru.sendel.practice.GroupOrders`  
  Группировка событий заказов и сортировка по времени.

Практические классы оформлены как учебные заготовки. Их удобно запускать из IDE.

## Требования
- JDK 21 (в `pom.xml` выставлены `maven.compiler.source/target = 21`)
- Maven 3.9+

Если запустить сборку на более старом JDK, Maven завершится ошибкой вида `invalid target release: 21`.

## Сборка

```bash
mvn clean compile
```

## Как запускать демо

### Важно для исследований с рефлексией
Для классов, где используется `MapReflectUtils` (рефлексия к внутренностям `HashMap`), обязательно добавляйте:

```bash
--add-opens java.base/java.util=ALL-UNNAMED
```

Это обязательно для запуска:
- `ru.sendel.CollisionDemo`
- `ru.sendel.TreeifyDemo`
- `ru.sendel.ResizeSpikeDemo`

### Примеры запуска из терминала

```bash
# Коллизии
java --add-opens java.base/java.util=ALL-UNNAMED -cp target/classes ru.sendel.CollisionDemo

# Treeify
java --add-opens java.base/java.util=ALL-UNNAMED -cp target/classes ru.sendel.TreeifyDemo

# Resize spikes
java --add-opens java.base/java.util=ALL-UNNAMED -cp target/classes ru.sendel.ResizeSpikeDemo

# Изменяемый ключ / null-ключ
java -cp target/classes ru.sendel.MissingKeys
```

## Запуск в IDE (IntelliJ IDEA)

1. Откройте проект как Maven-проект.
2. Для `CollisionDemo`, `TreeifyDemo`, `ResizeSpikeDemo` добавьте в Run Configuration в поле VM options:

```text
--add-opens java.base/java.util=ALL-UNNAMED
```

3. Запускайте нужный класс.

## Примечание по видео
Этот репозиторий предназначен как учебное приложение к видео: сначала можно смотреть объяснение, затем запускать соответствующее демо и повторять шаги локально.
