## Variables

- variable = a reusable **container** for a value

  - a variable behaves as if it was the value it contains
- Primitive = simple value stored directly in memory (stack)
- Reference = memory address (stack) that points to the (heap)

### Primitive vs Reference

| Primitive | Reference |
| --------- | --------- |
| int       | string    |
| double    | array     |
| char      | object    |
| boolean   |           |

### Copying behavior

### What is an object type?

An **object** is any instance created from a **class**. It's a reference type, meaning the variable holds an **address** pointing to the actual data in the Heap.

Everything created with `new` (or String literals) is an **object** stored in the **Heap**.
Primitive types (`int`, `double`, `char`, `boolean`) are just simple values stored directly in the **Stack**.

```java
// Scanner is an object
Scanner scanner = new Scanner(System.in);

// Arrays are objects
int[] numbers = {1, 2, 3};

// String is an object
String name = "Abdel";

// Any class you create becomes an object type
Car myCar = new Car();
```

```
Stack:                          Heap:
┌──────────┐                   ┌──────────────────┐
│ int a = 5│ ← value here     │                  │
├──────────┤                   │ Scanner object   │
│ scanner  │ ── address ──────►│ (fields, methods)│
├──────────┤                   ├──────────────────┤
│ name     │ ── address ──────►│ "Abdel"          │
└──────────┘                   └──────────────────┘
```

### Copying behavior

- **Primitive** → copies the **value**, independent (changing one does not affect the other)
- **Reference** → copies the **address**, both point to the same object in the Heap

#### Example

```java
// Primitive: copies the VALUE
int a = 5;
int b = a;   // b gets its own copy of 5
b = 10;
System.out.println(a); // 5 — not affected

// Reference: copies the ADDRESS (same object)
String x = "hello";
String y = x;   // y points to the same object as x
```
