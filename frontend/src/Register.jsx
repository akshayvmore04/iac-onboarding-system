import { useLocation } from "react-router-dom";
import { useEffect, useState } from "react";

export default function Register() {

  const location = useLocation();
  const query = new URLSearchParams(location.search);
  const ref = query.get("ref");

  const [form, setForm] = useState({
    name: "",
    email: "",
    college: ""
  });

  const API = "http://localhost:8080/api";

  //  Track click
  useEffect(() => {
    if (ref) {
      fetch(`${API}/track/${ref}`, { method: "POST" });
    }
  }, [ref]);

  const handleSubmit = async () => {
    await fetch(`${API}/register?ref=${ref}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(form)
    });

    alert("Registered successfully!");
  };

  return (
    <div style={{ padding: "40px" }}>
      <h1>Register via Referral</h1>
      <p>Referral Code: {ref}</p>

      <input
        placeholder="Name"
        onChange={(e) => setForm({ ...form, name: e.target.value })}
      />
      <br />

      <input
        placeholder="Email"
        onChange={(e) => setForm({ ...form, email: e.target.value })}
      />
      <br />

      <input
        placeholder="College"
        onChange={(e) => setForm({ ...form, college: e.target.value })}
      />
      <br />

      <button onClick={handleSubmit}>Register</button>
    </div>
  );
}